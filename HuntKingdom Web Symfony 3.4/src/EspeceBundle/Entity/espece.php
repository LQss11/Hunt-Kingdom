<?php

namespace EspeceBundle\Entity;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\Validator\Constraints as Assert;

use Doctrine\ORM\Mapping as ORM;

/**
 * espece
 *
 * @ORM\Table(name="espece")
 * @ORM\Entity(repositoryClass="EspeceBundle\Repository\especeRepository")
 */
class espece
{
    /**
     * @var int
     * @ORM\Column(name="idEspece", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idEspece;

    /**
     * @var string
     *
     * @ORM\Column(name="nomEspece", type="string", length=255)
     */
    private $nomEspece;

    /**
     * @ORm\ManyToOne(targetEntity="saison")
     * @ORM\JoinColumn(name="idS",referencedColumnName="idSaison")
      */
     private $idS;

    /**
     * @var string
     *
     * @ORM\Column(name="descriptionEspece", type="string", length=255)
     */
    private $descriptionEspece;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255)
     * @Assert\Image()
     * @Assert\NotBlank(message="plz enter an image")
     */

    private $image;

    /**
     * @var string
     *
     * @ORM\Column(name="poids", type="string", length=255)
     */
    private $poids;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="zone", type="string", length=255)
     */
    private $zone;

    /**
     * @var string
     *
     * @ORM\Column(name="ville", type="string", length=255)
     */
    private $ville;

    /**
     * @return int
     */
    public function getIdEspece()
    {
        return $this->idEspece;
    }

    /**
     * @param int $idEspece
     */
    public function setIdEspece($idEspece)
    {
        $this->idEspece = $idEspece;
    }




    /**
     * Set nomEspece
     *
     * @param string $nomEspece
     *
     * @return espece
     */
    public function setNomEspece($nomEspece)
    {
        $this->nomEspece = $nomEspece;

        return $this;
    }

    /**
     * Get nomEspece
     *
     * @return string
     */
    public function getNomEspece()
    {
        return $this->nomEspece;
    }

    /**
     * Set descriptionEspece
     *
     * @param string $descriptionEspece
     *
     * @return espece
     */
    public function setDescriptionEspece($descriptionEspece)
    {
        $this->descriptionEspece = $descriptionEspece;

        return $this;
    }

    /**
     * Get descriptionEspece
     *
     * @return string
     */
    public function getDescriptionEspece()
    {
        return $this->descriptionEspece;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return espece
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set poids
     *
     * @param string $poids
     *
     * @return espece
     */
    public function setPoids($poids)
    {
        $this->poids = $poids;

        return $this;
    }

    /**
     * Get poids
     *
     * @return string
     */
    public function getPoids()
    {
        return $this->poids;
    }

    /**
     * Set type
     *
     * @param string $type
     *
     * @return espece
     */
    public function setType($type)
    {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * Set zone
     *
     * @param string $zone
     *
     * @return espece
     */
    public function setZone($zone)
    {
        $this->zone = $zone;

        return $this;
    }

    /**
     * Get zone
     *
     * @return string
     */
    public function getZone()
    {
        return $this->zone;
    }

    /**
     * Set ville
     *
     * @param string $ville
     *
     * @return espece
     */
    public function setVille($ville)
    {
        $this->ville = $ville;

        return $this;
    }

    /**
     * Get ville
     *
     * @return string
     */
    public function getVille()
    {
        return $this->ville;
    }

    /**
     * @return mixed
     */
    public function getIdS()
    {
        return $this->idS;
    }

    /**
     * @param mixed $idS
     */
    public function setIdS($idS)
    {
        $this->idS = $idS;
    }



}
